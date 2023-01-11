package frc.lib.util;

import edu.wpi.first.math.geometry.Pose2d;

public class GameAprilTags {
    Pose2d[] targets = new Pose2d[8];

    public GameAprilTags () {
        targets[0] = new Pose2d();

    }

    public Pose2d getPose(int id){
        return targets[id - 1];
    }
}
