package einstein.improved_animations.util.data;

import java.util.List;

public enum TransformChannel {
    X,
    Y,
    Z,
    X_ROT,
    Y_ROT,
    Z_ROT;

    public static List<TransformChannel> rotationChannels(){
        return List.of(X_ROT, Y_ROT, Z_ROT);
    }

    public static List<TransformChannel> translateChannels(){
        return List.of(X, Y, Z);
    }
}
