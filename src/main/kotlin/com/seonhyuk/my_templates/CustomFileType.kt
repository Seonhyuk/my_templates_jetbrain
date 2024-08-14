package com.seonhyuk.my_templates

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.PlainTextLanguage
import icons.ExternalSystemIcons
import javax.swing.Icon

class CustomFileType : LanguageFileType(PlainTextLanguage.INSTANCE) {
    override fun getName(): String = "CustomFileType"
    override fun getDescription(): String = "Template file type"
    override fun getDefaultExtension(): String = "txt"
    override fun getIcon(): Icon = ExternalSystemIcons.Task
}
