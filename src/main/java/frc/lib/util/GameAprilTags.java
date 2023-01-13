package frc.lib.util;

import edu.wpi.first.math.geometry.Pose3d;

import edu.wpi.first.math.geometry.Rotation3d;
public class GameAprilTags {
    private static Pose3d[] targets = new Pose3d[8];
    private static GameAprilTags m_AprilTags;

    public static GameAprilTags getInstance(){
        if(m_AprilTags == null){
            m_AprilTags = new GameAprilTags();
        }
        return m_AprilTags;
    }

    public GameAprilTags () {
        targets[0] = new Pose3d(15.51, 1.07, 0.46, new Rotation3d(0, 0, Math.PI));
        targets[1] = new Pose3d(15.51, 2.74, 0.46, new Rotation3d(0, 0, Math.PI));
        targets[2] = new Pose3d(15.51, 4.42, 0.46, new Rotation3d(0, 0, Math.PI));
        targets[3] = new Pose3d(16.18, 6.75, 0.7, new Rotation3d(0, 0, Math.PI));
        targets[4] = new Pose3d(0.36, 6.75, 0.7, new Rotation3d());
        targets[5] = new Pose3d(1.03, 4.42, 0.46,  new Rotation3d());
        targets[6] = new Pose3d(1.03, 2.74, 0.46, new Rotation3d());
        targets[7] = new Pose3d(1.03, 1.07, 0.46, new Rotation3d());
    }

    public Pose3d getPose(int id){
        return targets[id - 1];
    }
}
