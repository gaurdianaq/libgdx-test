package com.mygdx.game.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.mygdx.components.AnimationComponent
import com.mygdx.components.SpriteComponent

class AnimationSystem : IteratingSystem(Family.all(SpriteComponent::class.java, AnimationComponent::class.java).get(), 1) {
    private val spriteMapper: ComponentMapper<SpriteComponent> = ComponentMapper.getFor(SpriteComponent::class.java)
    private val animationMapper: ComponentMapper<AnimationComponent> = ComponentMapper.getFor(AnimationComponent::class.java)

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val animation = animationMapper[entity]
        animation.stateTime += deltaTime
        spriteMapper[entity].sprite = animation.getCurrentFrame()
    }
}