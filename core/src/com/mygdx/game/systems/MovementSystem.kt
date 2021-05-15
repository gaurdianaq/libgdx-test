package com.mygdx.game.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.game.PositionComponent
import com.mygdx.game.VelocityComponent

class MovementSystem : IteratingSystem(Family.all(PositionComponent::class.java, VelocityComponent::class.java).get()) {
    private val positionMapper: ComponentMapper<PositionComponent> = ComponentMapper.getFor(PositionComponent::class.java)
    private val velocityMapper: ComponentMapper<VelocityComponent> = ComponentMapper.getFor(VelocityComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = positionMapper[entity]
        val velocity = velocityMapper[entity]

        position.x += velocity.x
        position.y += velocity.y
    }
}