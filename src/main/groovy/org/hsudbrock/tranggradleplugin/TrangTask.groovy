package org.hsudbrock.tranggradleplugin

import org.gradle.api.*	
import org.gradle.api.tasks.*

import org.apache.commons.io.FilenameUtils

import com.thaiopensource.relaxng.translate.Driver

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
		sourceDirectory.listFiles().each { inputFile ->
			if (SUPPORTED_EXTENSIONS.contains(FilenameUtils.getExtension(inputFile.name))) {
				File outputFile = new File(targetDirectory, FilenameUtils.removeExtension(inputFile.name) + '.' + targetExtension)
				println "${inputFile.name}: Translating to ${outputFile.name}"
				String[] trangParams = [inputFile.absolutePath, outputFile.absolutePath] as String[]
				new Driver().run(trangParams)
			} else {
				println "${inputFile.name} : Extension '${FilenameUtils.getExtension(inputFile.name)}' is not supported, skipping."
			}
		}
    }
}
