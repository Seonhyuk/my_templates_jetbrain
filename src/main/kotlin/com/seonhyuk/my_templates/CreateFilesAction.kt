package com.seonhyuk.my_templates

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.ui.Messages
import java.io.IOException

class CreateFilesAction(folderName: String, private val folder: VirtualFile) : AnAction(folderName) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val targetDirectory = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

        val input = Messages.showInputDialog(
            project,
            "Enter the name",
            "Input Required",
            null
        ) ?: return

        try {
            ApplicationManager.getApplication().runWriteAction {
                val newFolder = targetDirectory.createChildDirectory(this, input)
                createFilesFromTemplates(folder, newFolder, input)

                modifyIndexFile(targetDirectory, input)
            }
        } catch (ex: IOException) {
            Messages.showMessageDialog(
                project,
                "Failed to create files: ${ex.message}",
                "Error",
                Messages.getErrorIcon()
            )
        }
    }

    private fun createFilesFromTemplates(templateFolder: VirtualFile, targetFolder: VirtualFile, input: String) {
        for (file in templateFolder.children.filter { !it.isDirectory }) {
            val newFileName = file.name.replace("__NAME__", input)
            val newFile = targetFolder.createChildData(this, newFileName)

            val fileContent = VfsUtil.loadText(file).replace("__NAME__", input)
            VfsUtil.saveText(newFile, fileContent)
        }
    }

    private fun modifyIndexFile(targetDirectory: VirtualFile, input: String) {
        val indexFile = targetDirectory.findChild("index.ts") ?: return

        val content = VfsUtil.loadText(indexFile)
        val additionalContent = "\nexport * from './$input';\nexport { default as $input } from './$input';\n"

        ApplicationManager.getApplication().runWriteAction {
            VfsUtil.saveText(indexFile, content + additionalContent)
        }
    }
}