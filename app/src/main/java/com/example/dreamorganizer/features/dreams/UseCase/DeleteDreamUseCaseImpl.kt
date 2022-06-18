package com.example.dreamorganizer.features.dreams.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository

class DeleteDreamUseCaseImpl(private val repository: DreamsRepository)  : DeleteDreamUseCase() {

    override suspend fun execute(dreamDTO: DreamDTO) {
        repository.deleteDream(dreamDTO)
    }

}