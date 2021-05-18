package com.mygdx.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array as GDXArray

data class PositionComponent(var x: Float, var y: Float) : Component
data class VelocityComponent(var x: Float, var y: Float) : Component
data class SpeedComponent(var x: Float, var y: Float) : Component
data class SpriteComponent(var sprite: TextureRegion) : Component

class AnimationComponent(texture: Texture, speed: Float, rows: Int, columns: Int) : Component {
    private val animation: Animation<TextureRegion>
    var stateTime: Float = 0f

    fun getCurrentFrame(): TextureRegion {
        return animation.getKeyFrame(stateTime, true)
    }

    init {
        val temp: Array<Array<TextureRegion>> = TextureRegion.split(texture,
                texture.width / columns, texture.height / rows)
        println("temp height is ${temp.size} and temp width is ${temp[0].size}")
        val animationFrames: GDXArray<TextureRegion> = GDXArray(rows * columns)
        println("animationFrames size is ${animationFrames.size}")
        var index = 0
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                println(index)
                animationFrames.add(temp[i][j])
                ++index
            }
        }
        animation = Animation(speed, animationFrames)
    }
}

data class PlayerControlled(val name: String) : Component
class AIControlled() : Component

/**
 * HashMap to represent user input, Int's should be from Input.Keys
 */
data class InputComponent(var controls: HashMap<String, Int>) : Component