package com.mygdx.game

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Sprite

data class PositionComponent(var x: Float, var y: Float) : Component
data class VelocityComponent(var x: Float, var y: Float) : Component
data class SpeedComponent(var x: Float, var y: Float) : Component
data class SpriteComponent(var sprite: Sprite) : Component

data class PlayerControlled(val name: String) : Component
class AIControlled() : Component

/**
 * HashMap to represent user input, Int's should be from Input.Keys
 */
data class InputComponent(var controls: HashMap<String, Int>) : Component