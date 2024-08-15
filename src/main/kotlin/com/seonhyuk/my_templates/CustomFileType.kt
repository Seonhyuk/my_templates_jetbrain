package com.seonhyuk.my_templates

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.PlainTextLanguage
import javax.swing.Icon
import com.intellij.openapi.util.IconLoader

class CustomFileType : LanguageFileType(PlainTextLanguage.INSTANCE) {
    object TemplateIcon {
        val FILE: Icon = IconLoader.getIcon("/icons/icon.svg", TemplateIcon::class.java)
    }

    override fun getName(): String = "CustomFileType"
    override fun getDescription(): String = "Template file type"
    override fun getDefaultExtension(): String = "txt"
    override fun getIcon(): Icon = TemplateIcon.FILE
}
