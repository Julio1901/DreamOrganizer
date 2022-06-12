package com.example.dreamorganizer.UseCase

import com.example.dreamorganizer.model.TotalMoneyDTO
import com.example.dreamorganizer.repository.TotalMoneyRepository

class UpdateTotalMoneyUseCaseImpl(private val totalMoneyRepository: TotalMoneyRepository): UpdateTotalMoneyUseCase() {

    override suspend fun execute(totalMoney: TotalMoneyDTO) {totalMoneyRepository.updateMoney(totalMoney)}

}