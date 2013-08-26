package org.hsudbrock.tranggradleplugin

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.thaiopensource.relaxng.translate.Driver

class TrangPlugin implements Plugin<Project> {
    void apply(Project target) {
        target.task('trang', type: TrangTask, description: 'Translate schemas using Trang')
    }
}