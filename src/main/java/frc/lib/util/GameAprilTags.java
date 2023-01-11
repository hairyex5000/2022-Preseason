package frc.lib.util;

import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.math.geometry.Rotation2d;
public class GameAprilTags {
    private static Pose2d[] targets = new Pose2d[8];
    private static GameAprilTags m_AprilTags;

    public static GameAprilTags getInstance(){
        if(m_AprilTags == null){
            m_AprilTags = new GameAprilTags();
        }
        return m_AprilTags;
    }

    public GameAprilTags () {
        targets[0] = new Pose2d(15.51, 1.07, new Rotation2d());
        targets[1] = new Pose2d(15.51, 2.74, new Rotation2d());
        targets[2] = new Pose2d(15.51, 4.42, new Rotation2d());
        targets[3] = new Pose2d();
        targets[4] = new Pose2d();
        targets[5] = new Pose2d(1.03, 4.42, new Rotation2d(Math.PI));
        targets[6] = new Pose2d(1.03, 2.74, new Rotation2d(Math.PI));
        targets[7] = new Pose2d(1.03, 1.07, new Rotation2d(Math.PI));
    }

    public Pose2d getPose(int id){
        return targets[id - 1];
    }
}
