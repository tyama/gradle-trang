package org.hsudbrock.tranggradleplugin

import org.gradle.api.*	
import org.gradle.api.tasks.*

import org.apache.commons.io.FilenameUtils

import com.thaiopensource.relaxng.translate.Driver

import static groovy.io.FileType.FILES

class TrangTask extends DefaultTask {
    String[] SUPPORTED_EXTENSIONS = ['rng', 'rnc', 'dtd', 'xsd', 'xml']
	
	@Input
	String targetExtension = 'xsd'
	
	@InputDirectory
	File sourceDirectory = new File('src/main/schemas')
	
	@OutputDirectory
	File targetDirectory = new File('generated/schemas')
	
    @TaskAction
    def executeTrang() {
        sourceDirectory.eachFileRecurse(FILES) {file->
            if(SUPPORTED_EXTENSIONS.contains(FilenameUtils.getExtension(file.name))) {
                execute(file, (file.absolutePath-sourceDirectory.absolutePath - file.name).substring(1))
            }
        }
    }

    def execute(File inputFile, String path){
        File targetDir = path?new File(targetDirectory,path):targetDirectory
        if(!targetDir.exists()) targetDir.mkdirs()
        File outputFile = new File(targetDir, FilenameUtils.removeExtension(inputFile.name) + '.' + targetExtension)
        //println "${inputFile.name}: Translating to ${outputFile.absolutePath}"
        String[] trangParams = [inputFile.absolutePath, outputFile.absolutePath] as String[]
        new Driver().run(trangParams)
    }
}
