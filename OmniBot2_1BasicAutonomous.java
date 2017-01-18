package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "OmniBot Autonomous", group = "OmniBot")
//@Disabled
public class OmniBot2_1BasicAutonomous extends LinearOpMode {

    private OmniBot2_1Hardware robot = new OmniBot2_1Hardware();

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        while (runtime.time() <= 1){
        	robot.motorFl.setPower(-0.7);
        	robot.motorFr.setPower(-0.6);
        	robot.motorBl.setPower(-0.7);
        	robot.motorBr.setPower(-0.6);
        }
        robot.motorFl.setPower(0);
        robot.motorFr.setPower(0);
        robot.motorBl.setPower(0);
        robot.motorBr.setPower(0);

        //while (runtime.time() <= 3.5) {
        //    robot.launcher1.setPower(1);
        //    robot.launcher2.setPower(1);
        //    robot.conveyor.setPower(1);
        //}
        //robot.launcher1.setPower(0);
        //robot.launcher2.setPower(0);
        //robot.conveyor.setPower(0);

        while (runtime.time() <= 5.5){
        	robot.motorFl.setPower(-0.7);
        	robot.motorFr.setPower(-0.6);
        	robot.motorBl.setPower(-0.7);
        	robot.motorBr.setPower(-0.6);
        }
        robot.motorFl.setPower(0);
        robot.motorFr.setPower(0);
        robot.motorBl.setPower(0);
        robot.motorBr.setPower(0);
    }
}