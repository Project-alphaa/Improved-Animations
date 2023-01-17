//package einstein.improved_animations.gui;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.text.Text;
//import org.jetbrains.annotations.NotNull;
//
//import java.awt.*;
//import java.util.Objects;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
//import net.minecraft.client.gui.widget.EntryListWidget;
//import net.minecraft.client.render.BufferBuilder;
//import net.minecraft.client.render.GameRenderer;
//import net.minecraft.client.render.OverlayTexture;
//import net.minecraft.client.render.Tessellator;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.VertexFormat;
//import net.minecraft.client.render.VertexFormats;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.Util;
//import net.minecraft.util.math.Mth;
//import org.joml.Quaternionf;
//
//public class TestScreen extends Screen {
//
//    public static final Identifier BACKGROUND_LOCATION = new Identifier("textures/block/deepslate.png");
//    private EntityConfigList entityConfigList;
//
//
//    public TestScreen(Screen screen) {
//        super(Text.literal("Animation Settings"));
//    }
//
//    @Override
//    protected void init(){
//        this.entityConfigList = new EntityConfigList();
//        this.addSelectableChild(entityConfigList);
//    }
//
//    @Override
//    public void render(@NotNull MatrixStack poseStack, int mouseX, int mouseY, float partialTicks) {
//        this.renderStoneBackground(/*Util.getMillis() / 20F / 20F*/0);
//        TestScreen.drawCenteredText(poseStack, this.textRenderer, this.title, this.width / 2, 10, 0xFFFFFF);
//        TestScreen.drawCenteredText(poseStack, this.textRenderer, "i: " + String.valueOf(mouseX), this.width / 2, 20, 0xFFFFFF);
//        TestScreen.drawCenteredText(poseStack, this.textRenderer, "j: " + String.valueOf(mouseY), this.width / 2, 30, 0xFFFFFF);
//
//        // left to right top to bottom
//        float mouseXPercent = (float)mouseX / (float)this.width;
//        float mouseYPercent = (float)mouseY / (float)this.height;
//
//        int backgroundColor = 0x95000000;
//        TestScreen.fill(poseStack, this.width / 3, 48, this.width, this.height - 24, backgroundColor);
//
//
//        //Util.getMillis() / 20F
//
//        poseStack.push();
//        poseStack.translate(Mth.lerp(0.5, this.width / 3F, this.width), Mth.lerp(0.9, 48, this.height - 24), 0.0);
//        poseStack.multiply(new Quaternionf().rotateX((float)Math.toRadians(-10)));
//        poseStack.multiply(new Quaternionf().rotateY((float)Math.toRadians(-45)));
//        poseStack.multiply(new Quaternionf().rotateY((float)Math.toRadians((float) Mth.lerp(Math.sin(Util.getMeasuringTimeMs() / 1000F), 0, 20))));
//        float scale = 50;
//        poseStack.scale(scale, scale, scale);
//        poseStack.scale(-1, -1, -1);
//
//        VertexConsumerProvider.Immediate bufferSource = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
//
//        BlockState basePlateState = Blocks.SMOOTH_STONE_SLAB.getDefaultState();
//        float basePlateSize = 2;
//        poseStack.scale(3 / (basePlateSize + 1), 3 / (basePlateSize + 1), 3 / (basePlateSize + 1));
//        poseStack.translate(-basePlateSize / 2 - 1, 0, -basePlateSize / 2 - 1);
//        for(int x = 0; x < basePlateSize; x++){
//            poseStack.translate(1, 0, 0);
//            for(int z = 0; z < basePlateSize; z++){
//                poseStack.translate(0, 0, 1);
//                MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(basePlateState, poseStack, bufferSource, Color.WHITE.getRGB(), OverlayTexture.DEFAULT_UV);
//            }
//            poseStack.translate(0, 0, -basePlateSize);
//        }
//        bufferSource.draw();
//        poseStack.pop();
//
//        this.entityConfigList.render(poseStack, mouseX, mouseY, partialTicks);
//    }
//
//    public void renderStoneBackground(float i) {
//        Tessellator tesselator = Tessellator.getInstance();
//        BufferBuilder bufferBuilder = tesselator.getBuffer();
//        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
//        RenderSystem.setShaderTexture(0, OPTIONS_BACKGROUND_TEXTURE);
//        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
//        float f = 32.0f;
//        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
//        bufferBuilder.vertex(0.0, this.height, 0.0).texture(0.0f, (float)this.height / 32.0f + (float)i).color(64, 64, 64, 255).next();
//        bufferBuilder.vertex(this.width, this.height, 0.0).texture((float)this.width / 32.0f, (float)this.height / 32.0f + (float)i).color(64, 64, 64, 255).next();
//        bufferBuilder.vertex(this.width, 0.0, 0.0).texture((float)this.width / 32.0f, i).color(64, 64, 64, 255).next();
//        bufferBuilder.vertex(0.0, 0.0, 0.0).texture(0.0f, i).color(64, 64, 64, 255).next();
//        tesselator.draw();
//    }
//
//    public class EntityConfigList extends EntryListWidget<EntityConfigEntry> {
//
//        /*
//        public AbstractSelectionList(Minecraft minecraft, int i, int j, int k, int l, int m)
//
//        this.minecraft = minecraft;
//        this.width = i;
//        this.height = j;
//        this.y0 = k;
//        this.y1 = l;
//        this.itemHeight = m;
//        this.x0 = 0;
//        this.x1 = i;
//         */
//
//        public EntityConfigList() {
//            super(Objects.requireNonNull(TestScreen.this.client), 128, TestScreen.this.height, 48, TestScreen.this.height - 24, 24);
//
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "Bruhhhh"));
//            this.addEntry(new EntityConfigEntry(EntityConfigEntryType.HEADER, "bruhhhhhh"));
//        }
//
//
//
//        public void render(@NotNull MatrixStack poseStack, int mouseX, int mouseY, float partialTicks) {
//            //ScreenUtil.scissor(this.getRowLeft(), this.getTop(), this.getWidth(), this.getBottom() - this.getTop());
//            super.render(poseStack, mouseX, mouseY, partialTicks);
//            //RenderSystem.disableScissor();
//        }
//
//        @Override
//        public void appendNarrations(@NotNull NarrationMessageBuilder narrationElementOutput) {
//
//        }
//    }
//
//    public enum EntityConfigEntryType {
//        HEADER,
//        LABELED_OPTION
//    }
//
//    public static class EntityConfigEntry extends EntryListWidget.Entry<EntityConfigEntry> {
//
//        private final String text;
//
//        public EntityConfigEntry(EntityConfigEntryType entityConfigEntryType, String text){
//            this.text = text;
//        }
//
//        @Override
//        public void render(@NotNull MatrixStack poseStack, int index, int top, int left, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float partialTicks) {
//            if(hovered){
//                fill(poseStack, left, top, left + rowWidth, top + rowHeight, 0xFF9E0072);
//            }
//            TestScreen.drawCenteredText(poseStack, MinecraftClient.getInstance().textRenderer, text, left + rowWidth / 2, top + rowHeight / 2, 0xFFFFFF);
//        }
//    }
//}
