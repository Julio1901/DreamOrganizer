package com.example.dreamorganizer.UseCase

import com.example.dreamorganizer.model.TotalMoneyDTO
import com.example.dreamorganizer.repository.TotalMoneyRepository

class InsertTotalMoneyUseCaseImpl(private val totalMoneyRepository: TotalMoneyRepository): InsertTotalMoneyUseCase() {

    override suspend fun execute(totalMoney: TotalMoneyDTO) {
        totalMoneyRepository.insertTotalMoney(totalMoney)
    }

}