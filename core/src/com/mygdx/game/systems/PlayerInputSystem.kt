package com.mygdx.game.systems

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.mygdx.components.InputComponent
import com.mygdx.components.PlayerControlled
import com.mygdx.components.SpeedComponent
import com.mygdx.components.VelocityComponent

class PlayerInputSystem : IteratingSystem(Family.all(
        VelocityComponent::class.java,
        SpeedComponent::class.java,
        InputComponent::class.java,
        PlayerControlled::class.java).get(), 1) {

    private val velocityMapper: ComponentMapper<VelocityComponent> = ComponentMapper.getFor(VelocityComponent::class.java)
    private val inputMapper: ComponentMapper<InputComponent> = ComponentMapper.getFor(InputComponent::class.java)
    private val speedMapper: ComponentMapper<SpeedComponent> = ComponentMapper.getFor(SpeedComponent::class.java)

    private fun isKeyPressed(key: Int): Boolean {
        return Gdx.input.isKeyPressed(key)
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val velocity = velocityMapper[entity]
        val input = inputMapper[entity]
        val speed = speedMapper[entity]
        val isUpPressed = isKeyPressed(input.controls.getOrDefault("Up", Input.Keys.W))
        val isDownPressed = isKeyPressed(input.controls.getOrDefault("Down", Input.Keys.S))
        val isLeftPressed = isKeyPressed(input.controls.getOrDefault("Left", Input.Keys.A))
        val isRightPressed = isKeyPressed(input.controls.getOrDefault("Right", Input.Keys.D))
        val isSpacePressed = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        velocity.y = when {
            isUpPressed -> speed.y
            isDownPressed -> speed.y * -1
            else -> 0f
        }

        velocity.x = when {
            isLeftPressed -> speed.x * -1
            isRightPressed -> speed.x
            else -> 0f
        }
    }
}