package com.nikhil.slice.util

interface Mapper<Response, Entity, Model> {

    fun responseToEntity(data: Response): Entity

    fun entityToModel(entity: Entity): Model

}