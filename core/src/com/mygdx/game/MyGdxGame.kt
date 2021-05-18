package com.mygdx.game

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.components.*
import com.mygdx.game.systems.AnimationSystem
import com.mygdx.game.systems.MovementSystem
import com.mygdx.game.systems.PlayerInputSystem
import com.mygdx.game.systems.RenderSystem

class MyGdxGame : ApplicationAdapter() {
    private val engine = Engine()
    private lateinit var myTexture: Texture

    override fun create() {
        myTexture = Texture(Gdx.files.internal("shipspritesheetsmall.png"))

        val myInputs = HashMap<String, Int>()
        myInputs["Up"] = Input.Keys.W
        myInputs["Down"] = Input.Keys.S
        myInputs["Left"] = Input.Keys.A
        myInputs["Right"] = Input.Keys.D

        val playerEntity = Entity()
        playerEntity.add(PlayerControlled("Evan"))
        playerEntity.add(PositionComponent(-200f, 0f))
        playerEntity.add(VelocityComponent(0f, 0f))
        playerEntity.add(SpeedComponent(5f, 4f))
        playerEntity.add(SpriteComponent(TextureRegion()))
        playerEntity.add(AnimationComponent(myTexture, 0.1f, 1, 5))
        playerEntity.add(InputComponent(myInputs))

        engine.addEntity(playerEntity)
        engine.addSystem(AnimationSystem())
        engine.addSystem(RenderSystem(1600f, 900f))
        engine.addSystem(MovementSystem())
        engine.addSystem(PlayerInputSystem())
    }

    override fun render() {
        engine.update(Gdx.graphics.deltaTime)
    }

    override fun dispose() {

    }
}