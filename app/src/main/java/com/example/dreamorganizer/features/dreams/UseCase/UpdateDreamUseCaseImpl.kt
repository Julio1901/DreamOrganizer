package com.example.dreamorganizer.features.dreams.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository

class UpdateDreamUseCaseImpl(private val repository: DreamsRepository): UpdateDreamUseCase() {
    override suspend fun execute(dreamDTO: DreamDTO) {
       repository.updateDream(dreamDTO)
    }
}