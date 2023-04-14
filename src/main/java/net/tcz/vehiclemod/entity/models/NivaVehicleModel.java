package net.tcz.vehiclemod.entity.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.tcz.vehiclemod.entity.entities.VehicleEntity;
public class NivaVehicleModel extends EntityModel<VehicleEntity> {
	public final ModelPart body;
	public ModelPart hood;
	public ModelPart trunk;

	public ModelPart steeringWheel;
	public ModelPart radio;
	public ModelPart leftDoor;
	public ModelPart rightDoor;

	public ModelPart suspensionBody;
	public ModelPart suspensionFront;
	public ModelPart suspensionBack;

	public NivaVehicleModel(ModelPart root) {
		this.body = root.getChild("body");
		this.hood = body.getChild("hoodBase").getChild("hood");
		this.trunk = body.getChild("baseTrunk").getChild("trunk");

		this.steeringWheel = body.getChild("baseSteeringWheel").getChild("steeringWheel");
		this.radio = body.getChild("radioBase");
		this.leftDoor = body.getChild("doorLeft");
		this.rightDoor = body.getChild("doorRight");

		this.suspensionBody = body.getChild("floorBase").getChild("suspensionBase");
		this.suspensionFront = suspensionBody.getChild("frontSuspension");
		this.suspensionBack = suspensionBody.getChild("backSuspension");
	}
		public static TexturedModelData getTexturedModelData() {
			ModelData modelData = new ModelData();
			ModelPartData modelPartData = modelData.getRoot();
			ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(-0.0365F, 18.1065F, -0.3304F));

			ModelPartData frontFace = body.addChild("frontFace", ModelPartBuilder.create().uv(202, 25).cuboid(-17.5F, -7.5F, -0.05F, 34.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0365F, -4.1065F, -39.6696F));

			ModelPartData backFace = body.addChild("backFace", ModelPartBuilder.create().uv(76, 157).cuboid(-17.0F, -6.0F, 44.95F, 32.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5365F, -4.1065F, -4.6696F));

			ModelPartData radioBase = body.addChild("radioBase", ModelPartBuilder.create().uv(0, 145).cuboid(-4.0F, -5.45F, 5.2F, 8.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.4635F, -11.1065F, -26.1696F));

			ModelPartData floorBase = body.addChild("floorBase", ModelPartBuilder.create().uv(0, 70).cuboid(-19.0F, -2.5F, -19.3F, 38.0F, 3.0F, 40.0F, new Dilation(0.0F))
					.uv(156, 103).cuboid(-19.0F, -2.5F, -41.3F, 38.0F, 3.0F, 5.0F, new Dilation(0.0F))
					.uv(133, 25).cuboid(-13.0F, -2.5F, -36.3F, 26.0F, 3.0F, 17.0F, new Dilation(0.0F))
					.uv(133, 45).cuboid(-14.0F, -2.5F, 20.7F, 28.0F, 3.0F, 15.0F, new Dilation(0.0F))
					.uv(0, 173).cuboid(-19.0F, -2.5F, 35.7F, 38.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.4635F, 0.3935F, 0.6304F));

			ModelPartData suspensionBase = floorBase.addChild("suspensionBase", ModelPartBuilder.create(), ModelTransform.pivot(0.5F, 4.0F, 0.2F));

			ModelPartData r23_r1 = suspensionBase.addChild("r23_r1", ModelPartBuilder.create().uv(0, 63).cuboid(-57.5F, -0.5F, -1.5F, 29.0F, 3.0F, 3.0F, new Dilation(0.0F))
					.uv(0, 63).mirrored().cuboid(-28.5F, -0.5F, -1.5F, 29.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -1.0F, 28.5F, 0.0F, -1.5708F, 0.0F));

			ModelPartData backSuspension = suspensionBase.addChild("backSuspension", ModelPartBuilder.create().uv(0, 63).cuboid(-33.5F, 0.0F, -1.5F, 29.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(19.0F, -2.5F, 28.5F));

			ModelPartData frontSuspension = suspensionBase.addChild("frontSuspension", ModelPartBuilder.create().uv(133, 63).cuboid(-14.5F, -0.25F, -1.5F, 29.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.25F, -28.5F));

			ModelPartData hoodBase = body.addChild("hoodBase", ModelPartBuilder.create(), ModelTransform.of(-0.4635F, -19.0214F, -20.668F, 2.7751F, 0.0F, 0.0F));

			ModelPartData hood = hoodBase.addChild("hood", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

			ModelPartData cube_r1 = hood.addChild("cube_r1", ModelPartBuilder.create().uv(133, 0).cuboid(-16.0F, -0.2958F, -18.7272F, 32.0F, 5.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, -2.7053F, 0.0F, 0.0F));

			ModelPartData backSits = body.addChild("backSits", ModelPartBuilder.create().uv(78, 170).cuboid(-25.0F, -1.625F, 0.0F, 32.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(8.5365F, -9.4815F, 10.3304F));

			ModelPartData bone8 = backSits.addChild("bone8", ModelPartBuilder.create().uv(162, 170).cuboid(-25.0F, -15.1484F, 9.9886F, 32.0F, 17.0F, 4.0F, new Dilation(0.0F))
					.uv(249, 218).cuboid(-3.0F, -22.1484F, 9.9886F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F))
					.uv(219, 143).cuboid(-13.0F, -21.1484F, 10.4886F, 8.0F, 5.0F, 3.0F, new Dilation(0.0F))
					.uv(16, 1).cuboid(-22.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(16, 0).cuboid(-17.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(249, 76).cuboid(-23.0F, -22.1484F, 9.9886F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F))
					.uv(4, 5).cuboid(3.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(2, 5).cuboid(-12.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(0, 5).cuboid(-7.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(4, 4).cuboid(-2.0F, -16.1484F, 11.9886F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

			ModelPartData rightSit = body.addChild("rightSit", ModelPartBuilder.create().uv(218, 89).cuboid(-5.0F, -1.625F, -21.0F, 12.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(-11.4635F, -9.4815F, 10.3304F));

			ModelPartData bone7 = rightSit.addChild("bone7", ModelPartBuilder.create().uv(118, 233).cuboid(-5.0F, -14.2324F, -10.9914F, 12.0F, 17.0F, 4.0F, new Dilation(0.0F))
					.uv(248, 205).cuboid(-3.0F, -21.2324F, -10.9914F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F))
					.uv(4, 3).cuboid(3.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(4, 2).cuboid(-2.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

			ModelPartData leftSit = body.addChild("leftSit", ModelPartBuilder.create().uv(217, 0).cuboid(-5.0F, -1.625F, -21.0F, 12.0F, 4.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(8.5365F, -9.4815F, 10.3304F));

			ModelPartData bone6 = leftSit.addChild("bone6", ModelPartBuilder.create().uv(232, 55).cuboid(-5.0F, -14.2324F, -10.9914F, 12.0F, 17.0F, 4.0F, new Dilation(0.0F))
					.uv(234, 180).cuboid(-3.0F, -21.2324F, -10.9914F, 8.0F, 6.0F, 4.0F, new Dilation(0.0F))
					.uv(4, 1).cuboid(3.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
					.uv(4, 0).cuboid(-2.0F, -15.2324F, -8.9914F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

			ModelPartData doorLeft = body.addChild("doorLeft", ModelPartBuilder.create().uv(0, 113).cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F))
					.uv(133, 25).cuboid(-1.0F, -20.0F, 16.0F, 2.0F, 13.0F, 0.0F, new Dilation(0.0F))
					.uv(36, 244).cuboid(-1.0F, -22.0F, 5.0F, 2.0F, 2.0F, 11.0F, new Dilation(0.0F))
					.uv(115, 168).cuboid(0.0F, -20.0F, 0.0F, 0.0F, 12.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(16.5365F, -9.1065F, -16.6696F));

			ModelPartData cube_r2 = doorLeft.addChild("cube_r2", ModelPartBuilder.create().uv(36, 124).cuboid(-1.0F, -22.0F, -3.0F, 2.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

			ModelPartData doorRight = body.addChild("doorRight", ModelPartBuilder.create().uv(0, 113).mirrored().cuboid(-1.0F, -8.0F, 0.0F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
					.uv(36, 85).cuboid(-1.0F, -20.0F, 16.0F, 2.0F, 13.0F, 0.0F, new Dilation(0.0F))
					.uv(36, 244).mirrored().cuboid(-1.0F, -22.0F, 5.0F, 2.0F, 2.0F, 11.0F, new Dilation(0.0F)).mirrored(false)
					.uv(163, 175).cuboid(0.0F, -20.0F, 0.0F, 0.0F, 12.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(-17.4635F, -9.1065F, -16.6696F));

			ModelPartData cube_r3 = doorRight.addChild("cube_r3", ModelPartBuilder.create().uv(64, 39).cuboid(-1.0F, -22.0F, -3.0F, 2.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

			ModelPartData baseTrunk = body.addChild("baseTrunk", ModelPartBuilder.create(), ModelTransform.pivot(-0.4385F, -33.3351F, 27.6873F));

			ModelPartData trunk = baseTrunk.addChild("trunk", ModelPartBuilder.create().uv(218, 154).cuboid(-11.025F, 16.2286F, 9.6431F, 22.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

			ModelPartData cube_r4 = trunk.addChild("cube_r4", ModelPartBuilder.create().uv(0, 219).cuboid(-25.45F, 0.8456F, -0.2486F, 22.0F, 20.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(14.475F, 0.0847F, -1.1802F, 0.576F, 0.0F, 0.0F));

			ModelPartData baseSteeringWheel = body.addChild("baseSteeringWheel", ModelPartBuilder.create(), ModelTransform.of(9.569F, -17.1733F, -14.1589F, 0.3927F, 0.0F, 0.0F));

			ModelPartData steeringWheel = baseSteeringWheel.addChild("steeringWheel", ModelPartBuilder.create().uv(243, 33).cuboid(-4.9675F, -5.8417F, 0.0453F, 10.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

			ModelPartData baseCar = body.addChild("baseCar", ModelPartBuilder.create().uv(113, 113).cuboid(-17.2135F, -38.9815F, -13.6696F, 34.0F, 3.0F, 38.0F, new Dilation(0.0F))
					.uv(118, 205).cuboid(-17.2135F, -31.2315F, 0.3304F, 0.0F, 12.0F, 16.0F, new Dilation(0.0F))
					.uv(85, 184).cuboid(13.0365F, -10.9815F, 17.3304F, 6.0F, 12.0F, 18.0F, new Dilation(0.0F))
					.uv(85, 184).mirrored().cuboid(-19.3385F, -10.9815F, 17.3304F, 6.0F, 12.0F, 18.0F, new Dilation(0.0F)).mirrored(false)
					.uv(85, 184).mirrored().cuboid(-19.3385F, -10.9815F, -39.6696F, 6.0F, 12.0F, 18.0F, new Dilation(0.0F)).mirrored(false)
					.uv(85, 184).cuboid(13.0365F, -10.9815F, -39.6696F, 6.0F, 12.0F, 18.0F, new Dilation(0.0F))
					.uv(150, 205).cuboid(16.7865F, -31.2315F, 0.3304F, 0.0F, 12.0F, 16.0F, new Dilation(0.0F))
					.uv(214, 225).cuboid(16.7865F, -31.2315F, 20.3304F, 0.0F, 12.0F, 12.0F, new Dilation(0.0F))
					.uv(150, 221).cuboid(-17.2135F, -31.2315F, 20.3304F, 0.0F, 12.0F, 12.0F, new Dilation(0.0F))
					.uv(0, 113).cuboid(-18.2135F, -36.2315F, -15.6696F, 36.0F, 3.0F, 41.0F, new Dilation(0.0F))
					.uv(0, 0).cuboid(-16.2135F, -5.2315F, -35.6696F, 32.0F, 1.0F, 69.0F, new Dilation(0.0F))
					.uv(0, 180).cuboid(15.7865F, -19.2315F, -42.6696F, 2.0F, 16.0F, 23.0F, new Dilation(0.0F))
					.uv(0, 58).cuboid(-16.2135F, -21.2315F, -22.6696F, 32.0F, 3.0F, 2.0F, new Dilation(0.0F))
					.uv(219, 111).cuboid(15.7865F, -19.2315F, 20.3304F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F))
					.uv(82, 214).cuboid(15.7865F, -19.2315F, 0.3304F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F))
					.uv(52, 230).cuboid(15.7865F, -33.2315F, 0.3304F, 2.0F, 2.0F, 16.0F, new Dilation(0.0F))
					.uv(133, 11).cuboid(15.7865F, -33.2315F, 20.3304F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
					.uv(113, 113).cuboid(-18.2135F, -33.2315F, 20.3304F, 2.0F, 3.0F, 6.0F, new Dilation(0.0F))
					.uv(32, 226).cuboid(-18.2135F, -33.2315F, 0.3304F, 2.0F, 2.0F, 16.0F, new Dilation(0.0F))
					.uv(82, 214).mirrored().cuboid(-18.2135F, -19.2315F, 0.3304F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
					.uv(219, 111).mirrored().cuboid(-18.2135F, -19.2315F, 20.3304F, 2.0F, 16.0F, 16.0F, new Dilation(0.0F)).mirrored(false)
					.uv(4, 2).cuboid(-16.2135F, -19.2315F, 32.3304F, 5.0F, 16.0F, 4.0F, new Dilation(0.0F))
					.uv(211, 191).cuboid(-11.2135F, -13.2315F, 32.3304F, 22.0F, 10.0F, 4.0F, new Dilation(0.0F))
					.uv(50, 19).cuboid(10.7865F, -19.2315F, 32.3304F, 5.0F, 16.0F, 4.0F, new Dilation(0.0F))
					.uv(24, 241).cuboid(15.7865F, -33.2315F, -3.6696F, 2.0F, 30.0F, 4.0F, new Dilation(0.0F))
					.uv(24, 241).mirrored().cuboid(-18.2135F, -33.2315F, -3.6696F, 2.0F, 30.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
					.uv(0, 241).cuboid(15.7865F, -33.2315F, 16.3304F, 2.0F, 30.0F, 4.0F, new Dilation(0.0F))
					.uv(0, 241).mirrored().cuboid(-18.2135F, -33.2315F, 16.3304F, 2.0F, 30.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
					.uv(0, 180).mirrored().cuboid(-18.2135F, -19.2315F, -42.6696F, 2.0F, 16.0F, 23.0F, new Dilation(0.0F)).mirrored(false)
					.uv(199, 63).cuboid(-4.2135F, -14.2315F, -23.6696F, 8.0F, 9.0F, 17.0F, new Dilation(0.0F))
					.uv(0, 157).cuboid(-16.2135F, -9.2315F, -12.6696F, 32.0F, 4.0F, 12.0F, new Dilation(0.0F))
					.uv(142, 154).cuboid(-16.2135F, -9.2315F, 8.3304F, 32.0F, 4.0F, 12.0F, new Dilation(0.0F))
					.uv(116, 70).cuboid(-16.2135F, -17.2315F, -42.6696F, 32.0F, 14.0F, 19.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.25F, 2.125F, 3.0F));

			ModelPartData cube_r5 = baseCar.addChild("cube_r5", ModelPartBuilder.create().uv(174, 229).cuboid(-5.5F, -2.5F, -4.25F, 12.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(9.2865F, -17.2315F, -21.4196F, 0.3927F, 0.0F, 0.0F));

			ModelPartData cube_r6 = baseCar.addChild("cube_r6", ModelPartBuilder.create().uv(141, 245).cuboid(-1.0F, 2.0F, -4.5F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(2.7865F, -14.2315F, -0.1696F, -0.8727F, 0.0F, 0.0F));

			ModelPartData cube_r7 = baseCar.addChild("cube_r7", ModelPartBuilder.create().uv(27, 19).cuboid(-4.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
					.uv(0, 0).cuboid(-4.0F, -3.0F, 0.0F, 2.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.2135F, -14.2315F, -13.6696F, 1.5708F, 1.3526F, 1.5708F));

			ModelPartData cube_r8 = baseCar.addChild("cube_r8", ModelPartBuilder.create().uv(217, 14).cuboid(-28.0F, 8.5F, 39.65F, 22.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(16.7865F, -27.7198F, -17.5901F, 0.3927F, 0.0F, 0.0F));

			ModelPartData cube_r9 = baseCar.addChild("cube_r9", ModelPartBuilder.create().uv(0, 180).cuboid(-32.45F, 0.8456F, -1.2486F, 7.0F, 20.0F, 3.0F, new Dilation(0.0F))
					.uv(85, 246).cuboid(-3.6F, 0.8456F, -1.2486F, 7.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(14.2865F, -35.3754F, 23.5072F, 0.576F, 0.0F, 0.0F));

			ModelPartData cube_r10 = baseCar.addChild("cube_r10", ModelPartBuilder.create().uv(0, 39).cuboid(0.9F, -17.25F, -0.25F, 32.0F, 19.0F, 0.0F, new Dilation(0.0F))
					.uv(105, 246).cuboid(32.9F, -16.25F, -1.25F, 2.0F, 18.0F, 3.0F, new Dilation(0.0F))
					.uv(218, 249).cuboid(-0.9F, -16.25F, -1.25F, 2.0F, 18.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-17.2135F, -19.7315F, -21.1696F, -0.3927F, 0.0F, 0.0F));

			ModelPartData cube_r11 = baseCar.addChild("cube_r11", ModelPartBuilder.create().uv(27, 0).cuboid(-0.5F, -1.5F, -4.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(9.2865F, -18.7315F, -15.6696F, 0.3054F, 0.0F, 0.0F));

			ModelPartData cube_r12 = baseCar.addChild("cube_r12", ModelPartBuilder.create().uv(0, 70).cuboid(-1.0F, -6.0F, 5.5F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F))
					.uv(27, 0).cuboid(5.0F, -6.75F, -0.5F, 10.0F, 9.0F, 10.0F, new Dilation(0.0F))
					.uv(208, 34).cuboid(-14.0F, -7.75F, -1.5F, 12.0F, 10.0F, 11.0F, new Dilation(0.0F))
					.uv(0, 113).cuboid(-16.0F, -6.0F, 2.5F, 2.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-0.2135F, -12.2315F, -37.1696F, 0.0873F, 0.0F, 0.0F));

			ModelPartData cube_r13 = baseCar.addChild("cube_r13", ModelPartBuilder.create().uv(27, 184).cuboid(-16.0F, -5.45F, -5.3F, 32.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.2135F, -13.2315F, -21.1696F, -0.48F, 0.0F, 0.0F));

			ModelPartData cube_r14 = baseCar.addChild("cube_r14", ModelPartBuilder.create().uv(20, 70).cuboid(-2.0F, -4.5F, -3.0F, 4.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.7865F, -14.8527F, -35.8932F, 1.5708F, -1.4835F, -1.5708F));
			return TexturedModelData.of(modelData, 288, 288);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(VehicleEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}