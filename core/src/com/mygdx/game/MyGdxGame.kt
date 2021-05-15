package com.mygdx.game

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.systems.MovementSystem
import com.mygdx.game.systems.PlayerInputSystem
import com.mygdx.game.systems.RenderSystem

class MyGdxGame : ApplicationAdapter() {
    private val engine = Engine()
    private lateinit var myTexture: Texture

    override fun create() {
        myTexture = Texture(Gdx.files.internal("badlogic.jpg"))
        val myInputs = HashMap<String, Int>()
        myInputs["Up"] = Input.Keys.W
        myInputs["Down"] = Input.Keys.S
        myInputs["Left"] = Input.Keys.A
        myInputs["Right"] = Input.Keys.D

        val myInputs2 = HashMap<String, Int>()
        myInputs2["Up"] = Input.Keys.UP
        myInputs2["Down"] = Input.Keys.DOWN
        myInputs2["Left"] = Input.Keys.LEFT
        myInputs2["Right"] = Input.Keys.RIGHT

        val playerEntity = Entity()
        playerEntity.add(PlayerControlled("Evan"))
        playerEntity.add(PositionComponent(-200f, 0f))
        playerEntity.add(VelocityComponent(0f, 0f))
        playerEntity.add(SpeedComponent(0.5f, 0.5f))
        playerEntity.add(SpriteComponent(Sprite(myTexture)))
        playerEntity.add(InputComponent(myInputs))

        val playerEntity2 = Entity()
        playerEntity2.add(PlayerControlled("Thomas"))
        playerEntity2.add(PositionComponent(50f, -200f))
        playerEntity2.add(VelocityComponent(0f, 0f))
        playerEntity2.add(SpeedComponent(1.5f, 1.5f))
        playerEntity2.add(SpriteComponent(Sprite(myTexture)))
        playerEntity2.add(InputComponent(myInputs2))

        val playerEntity3 = Entity()
        playerEntity3.add(PlayerControlled("Shamila"))
        playerEntity3.add(PositionComponent(200f, 0f))
        playerEntity3.add(VelocityComponent(0f, 0f))
        playerEntity3.add(SpeedComponent(-0.5f, -0.5f))
        playerEntity3.add(SpriteComponent(Sprite(myTexture)))
        playerEntity3.add(InputComponent(myInputs))

        engine.addEntity(playerEntity)
        engine.addEntity(playerEntity2)
        engine.addEntity(playerEntity3)
        engine.addSystem(RenderSystem(800f, 600f))
        engine.addSystem(MovementSystem())
        engine.addSystem(PlayerInputSystem())
    }

    override fun render() {
        engine.update(Gdx.graphics.deltaTime)
    }

    override fun dispose() {

    }
}