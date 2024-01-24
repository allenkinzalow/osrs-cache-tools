package com.kinztech.cache.sql.transactions

import com.google.gson.Gson
import com.kinztech.cache.sql.schema.Widgets
import net.runelite.cache.InterfaceManager
import net.runelite.cache.fs.Store
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class WidgetTransaction: Transaction {

    override fun execute(store: Store) {
        val interfaceManager = InterfaceManager(store)
        interfaceManager.load()
        interfaceManager.export( File("interfaces"))
       /* transaction {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Widgets)
            val gson = Gson()

            // Clear all old widgets.
            Widgets.deleteAll()

            val interfaceManager = InterfaceManager(store)
            interfaceManager.load()


            Widgets.batchInsert(components.components) { component ->
                this[Widgets.id] = component.id
                this[Widgets.isIf3] = component.isIf3
                this[Widgets.type] = component.type
                this[Widgets.menuType] = component.menuType
                this[Widgets.contentType] = component.contentType.toInt()
                this[Widgets.originalX] = component.originalX.toInt()
                this[Widgets.originalY] = component.originalY.toInt()
                this[Widgets.originalWidth] = component.originalWidth
                this[Widgets.originalHeight] = component.originalHeight
                this[Widgets.opacity] = component.opacity.toInt()
                this[Widgets.parentId] = component.parentId
                this[Widgets.hoverSiblingId] = component.hoveredSiblingId?.toInt()
                this[Widgets.scrollWidth] = component.scrollWidth.toInt()
                this[Widgets.scrollHeight] = component.scrollHeight.toInt()
                this[Widgets.isHidden] = component.isHidden
                this[Widgets.clickMask] = component.clickMask.toInt()
                this[Widgets.xPitch] = component.xPitch.toInt()
                this[Widgets.yPitch] = component.yPitch.toInt()
                this[Widgets.xOffsets] = component.xOffsets?.contentToString().toString()
                this[Widgets.yOffsets] = component.yOffsets?.contentToString().toString()
                this[Widgets.sprites] = component.sprites?.contentToString().toString()
                this[Widgets.configActions] = component.configActions?.contentToString().toString()
                this[Widgets.isFilled] = component.isFilled
                this[Widgets.xTextAlignment] = component.xTextAlignment.toInt()
                this[Widgets.yTextAlignment] = component.yTextAlignment.toInt()
                this[Widgets.lineHeight] = component.lineHeight.toInt()
                this[Widgets.fontId] = component.fontId?.toInt()
                this[Widgets.textShadowed] = component.textShadowed
                this[Widgets.text] = component.text
                this[Widgets.alternateText] = component.alternateText
                this[Widgets.textColor] = component.textColor
                this[Widgets.alternateTextColor] = component.alternateTextColor
                this[Widgets.hoveredTextColor] = component.hoveredTextColor
                this[Widgets.spriteId] = component.spriteId
                this[Widgets.alternateSpriteId] = component.alternateSpriteId
                this[Widgets.modelType] = component.modelType
                this[Widgets.modelId] = component.modelId?.toInt()
                this[Widgets.alternateModelId] = component.alternateModelId?.toInt()
                this[Widgets.animationId] = component.animationId?.toInt()
                this[Widgets.alternateAnimationId] = component.alternateAnimationId?.toInt()
                this[Widgets.modelZoom] = component.modelZoom.toInt()
                this[Widgets.rotationX] = component.rotationX.toInt()
                this[Widgets.rotationY] = component.rotationY.toInt()
                this[Widgets.rotationZ] = component.rotationZ.toInt()
                this[Widgets.targetVerb] = component.targetVerb
                this[Widgets.spellName] = component.spellName
                this[Widgets.tooltip] = component.tooltip
                this[Widgets.widthMode] = component.widthMode.toInt()
                this[Widgets.heightMode] = component.heightMode.toInt()
                this[Widgets.xPositionMode] = component.xPositionMode.toInt()
                this[Widgets.yPositionMode] = component.yPositionMode.toInt()
                this[Widgets.noClickThrough] = component.noClickThrough
                this[Widgets.textureId] = component.textureId.toInt()
                this[Widgets.spriteTiling] = component.spriteTiling
                this[Widgets.borderType] = component.borderType.toInt()
                this[Widgets.shadowColor] = component.shadowColor
                this[Widgets.flippedVertically] = component.flippedVertically
                this[Widgets.flippedHorizontally] = component.flippedHorizontally
                this[Widgets.offsetX2d] = component.offsetX2d.toInt()
                this[Widgets.offsetY2d] = component.offsetY2d.toInt()
                this[Widgets.orthogonal] = component.orthogonal
                this[Widgets.modelHeightOverride] = component.modelHeightOverride.toInt()
                this[Widgets.lineWidth] = component.lineWidth.toInt()
                this[Widgets.lineDirection] = component.lineDirection
                this[Widgets.name] = component.name
                this[Widgets.actions] = component.actions?.contentToString().toString()
                this[Widgets.dragDeadZone] = component.dragDeadZone.toInt()
                this[Widgets.dragDeadTime] = component.dragDeadTime.toInt()
                this[Widgets.dragRenderBehavior] = component.dragRenderBehavior
                this[Widgets.clientScripts] = gson.toJson(component.clientScripts).toString()
                this[Widgets.onLoadListener] = component.onLoadListener?.contentToString().toString()
                this[Widgets.onMouseOverListener] = component.onMouseOverListener?.contentToString().toString()
                this[Widgets.onMouseLeaveListener] = component.onMouseLeaveListener?.contentToString().toString()
                this[Widgets.onTargetLeaveListener] = component.onTargetLeaveListener?.contentToString().toString()
                this[Widgets.onTargetEnterListener] = component.onTargetEnterListener?.contentToString().toString()
                this[Widgets.onVarTransmitListener] = component.onVarTransmitListener?.contentToString().toString()
                this[Widgets.onInvTransmitListener] = component.onInvTransmitListener?.contentToString().toString()
                this[Widgets.onStatTransmitListener] = component.onStatTransmitListener?.contentToString().toString()
                this[Widgets.onTimerListener] = component.onTimerListener?.contentToString().toString()
                this[Widgets.onOpListener] = component.onOpListener?.contentToString().toString()
                this[Widgets.onMouseRepeatListener] = component.onMouseRepeatListener?.contentToString().toString()
                this[Widgets.onClickListener] = component.onClickListener?.contentToString().toString()
                this[Widgets.onClickRepeatListener] = component.onClickRepeatListener?.contentToString().toString()
                this[Widgets.onReleaseListener] = component.onReleaseListener?.contentToString().toString()
                this[Widgets.onHoldListener] = component.onHoldListener?.contentToString().toString()
                this[Widgets.onDragListener] = component.onDragListener?.contentToString().toString()
                this[Widgets.onDragCompleteListener] = component.onDragCompleteListener?.contentToString().toString()
                this[Widgets.onScrollWheelListener] = component.onScrollWheelListener?.contentToString().toString()
                this[Widgets.varTransmitTriggers] = component.varTransmitTriggers?.contentToString().toString()
                this[Widgets.invTransmitTriggers] = component.invTransmitTriggers?.contentToString().toString()
                this[Widgets.statTransmitTriggers] = component.statTransmitTriggers?.contentToString().toString()

            }
            println("Exported widget definitions to SQL Table.")
        }*/
    }

}
