package com.nikhil.slice.util

interface Mapper<Response, Entity, Model> {

    /**
     * For implementing mapping mechanism for Network Response to Database Entity
     * */
    fun responseToEntity(data: Response): Entity

    /**
     * For implementing mapping mechanism for Database Entity to UI Model
     * */
    fun entityToModel(entity: Entity): Model

}