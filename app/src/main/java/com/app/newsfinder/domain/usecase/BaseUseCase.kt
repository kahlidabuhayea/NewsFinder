package com.app.newsfinder.domain.usecase

abstract class BaseUseCase<out Type, in Params>  {

    abstract suspend fun execute(params: Params): Type

    class None
}