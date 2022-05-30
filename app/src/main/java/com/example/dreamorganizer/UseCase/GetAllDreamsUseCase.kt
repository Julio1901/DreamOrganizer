package com.example.dreamorganizer.UseCase

import com.example.dreamorganizer.features.dreams.model.DreamDTO

abstract  class GetAllDreamsUseCase : UseCaseWithoutParameters<List<DreamDTO>>()