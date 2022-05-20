package com.muxiu1997.sharewhereiam.localizationgenerator

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.muxiu1997.sharewhereiam.localization.Localization

class LocalizationSymbolProcessor(private val env: SymbolProcessorEnvironment) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annoType = resolver.getClassDeclarationByName(
            resolver.getKSNameFromString(LocalizationAnnoName)
        )?.asType(emptyList())!!

        resolver.getSymbolsWithAnnotation(LocalizationAnnoName)
            .filterIsInstance<KSPropertyDeclaration>()
            .forEach {
                LocalizationGenerator.add(it, annoType)
            }

        LocalizationGenerator.generate(env.codeGenerator)
        return emptyList()
    }

    companion object {
        val LocalizationAnnoName = requireNotNull(Localization::class.qualifiedName)
    }
}
