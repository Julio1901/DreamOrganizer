package com.example.dreamorganizer.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository

class GetAllDreamsUseCaseImpl (private val repository: DreamsRepository) : GetAllDreamsUseCase() {

    override suspend fun execute(): List<DreamDTO> {
        return repository.getAllDreams()
    }

}