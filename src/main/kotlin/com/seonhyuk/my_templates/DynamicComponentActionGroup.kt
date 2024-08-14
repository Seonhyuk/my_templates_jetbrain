package com.seonhyuk.my_templates

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.vfs.VirtualFileManager

class DynamicComponentActionGroup : DefaultActionGroup() {
    override fun getChildren(event: AnActionEvent?): Array<AnAction> {
        val project = event?.project ?: return emptyArray()
        val basePath = project.basePath ?: return emptyArray()
        val targetFolder = VirtualFileManager.getInstance().findFileByUrl("file://$basePath/templates") ?: return emptyArray()

        val actions = mutableListOf<AnAction>()
        for (subFolder in targetFolder.children.filter { it.isDirectory }) {
            if (subFolder.children.isNotEmpty()) {
                actions.add(CreateFilesAction(subFolder.name, subFolder))
            }
        }

        return actions.toTypedArray()
    }
}