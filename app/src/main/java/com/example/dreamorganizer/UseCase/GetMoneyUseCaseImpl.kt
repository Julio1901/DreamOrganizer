package com.example.dreamorganizer.UseCase

import com.example.dreamorganizer.model.TotalMoneyDTO
import com.example.dreamorganizer.repository.TotalMoneyRepository

class GetMoneyUseCaseImpl(private val totalMoneyRepository: TotalMoneyRepository): GetMoneyUseCase() {

    override suspend fun execute(): List<TotalMoneyDTO> = totalMoneyRepository.getMoney()

}