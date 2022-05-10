package com.example.dreamorganizer.features.dreams.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository


class GetDreamUseCaseImpl(private val repository: DreamsRepository) : GetDreamUseCase() {

    override suspend fun execute(paramrs: Int): DreamDTO {
        //return repository.getDream(paramrs)
        return DreamDTO(0, "Test mock", 100F, null)
    }

}