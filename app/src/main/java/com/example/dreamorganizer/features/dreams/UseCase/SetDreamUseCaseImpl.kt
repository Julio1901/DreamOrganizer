package com.example.dreamorganizer.features.dreams.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository

class SetDreamUseCaseImpl(private val repository: DreamsRepository) : SetDreamUseCase() {

    override suspend fun execute(paramrs: DreamDTO) {
        repository.saveDream(paramrs)
    }

}