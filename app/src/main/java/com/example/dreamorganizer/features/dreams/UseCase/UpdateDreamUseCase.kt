package com.example.dreamorganizer.features.dreams.UseCase

import com.example.dreamorganizer.UseCase.UseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO

abstract class UpdateDreamUseCase: UseCase<DreamDTO, Unit>()