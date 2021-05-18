package com.mygdx.game.systems

import com.badlogic.ashley.core.*
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.components.PositionComponent
import com.mygdx.components.SpriteComponent

class RenderSystem(cameraWidth: Float, cameraHeight: Float) : EntitySystem(2) {
    private lateinit var entities: ImmutableArray<Entity>

    private val positionMapper: ComponentMapper<PositionComponent> = ComponentMapper.getFor(PositionComponent::class.java)
    private val spriteMapper: ComponentMapper<SpriteComponent> = ComponentMapper.getFor(SpriteComponent::class.java)
    private val camera = OrthographicCamera(cameraWidth, cameraHeight)
    private val batch: SpriteBatch = SpriteBatch()

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        entities = engine.getEntitiesFor(Family.all(PositionComponent::class.java, SpriteComponent::class.java).get())
    }

    override fun update(deltaTime: Float) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)
        camera.update()
        batch.projectionMatrix = camera.combined

        batch.begin()
        for (entity in entities) {
            val sprite = spriteMapper[entity].sprite
            val position = positionMapper[entity]
            batch.draw(sprite, position.x, position.y)
        }
        batch.end()
    }
}