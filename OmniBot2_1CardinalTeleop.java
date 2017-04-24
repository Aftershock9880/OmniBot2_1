package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static com.qualcomm.robotcore.util.Range.clip;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

@TeleOp(name = "OmniBot 2.1 Cardinal Teleop", group = "OmniBot 2.1")
//@Disabled
public class OmniBot2_1CardinalTeleop extends OpMode {

    private OmniBot2_1Hardware robot = new OmniBot2_1Hardware();

	private double powerDivider = 1; //Divide power by this much

    private double Flpower;
    private double Frpower;
    private double Blpower;
    private double Brpower;

    private double joyX = 0;
    private double joyY = 0;
    private double joyTheta = 0;
    private double joyRadius = 0;
    private double robotDir = 0;
    private double DirMod = 0;

    double targetDir = 0;

    double lastP = 0;

    double kp = 1/600;
    double ki = 1/600;
    double kd = 1/600;

    double p = 0;
    double i = 0;
    double d = 0;

    double finalPD = 0;

    @Override
	public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void start() {
        robot.gyro.calibrate();

    }

	@Override
	public void loop() {
        joyX = -gamepad1.left_stick_x;
        joyY = -gamepad1.left_stick_y;
        joyTheta  = atan2(joyY, joyX);
        joyRadius = sqrt((joyX*joyX) + (joyY*joyY));

        p = toDegrees(robotDir) - targetDir;
        i =+ p;
        d = p - lastP;
        lastP = p;
        finalPD = p * kp + /*i * ki +*/ d * kd;

        robotDir  = -Math.toRadians(robot.gyro.getHeading() + 45) - DirMod;

        // movement code, Gamepad 1 controls movement with left stick and eventually turning with right stick
        Flpower =  cos(robotDir + joyTheta) * joyRadius +  gamepad1.right_stick_x * 0.7;
        Frpower = -sin(robotDir + joyTheta) * joyRadius + -gamepad1.right_stick_x * 0.7;
        Blpower = -sin(robotDir + joyTheta) * joyRadius +  gamepad1.right_stick_x * 0.7;
        Brpower =  cos(robotDir + joyTheta) * joyRadius + -gamepad1.right_stick_x * 0.7;

		robot.motorFl.setPower(Flpower + finalPD);
		robot.motorFr.setPower(Frpower - finalPD);
		robot.motorBl.setPower(Blpower + finalPD);
		robot.motorBr.setPower(Brpower - finalPD);

        if (gamepad1.left_bumper) {
            DirMod =+ 0.01;
        }

        if (gamepad1.right_bumper) {
            DirMod =- 0.01;
        }

        /*/ lift code
        if (gamepad1.y) {
            robot.motorLift.setPower(1);
        }
        else if (gamepad1.a) {
            robot.motorLift.setPower(-1);
        }
        else {
            robot.motorLift.setPower(0);
        }*/

		//Send telemetry data back to driver station.
		telemetry.addData("stick X: ", -gamepad1.left_stick_x);
		telemetry.addData("stick Y: ", -gamepad1.left_stick_y);
        telemetry.addData("stick Theta: ",  joyTheta );
		telemetry.addData("stick Radius: ", joyRadius);
        telemetry.addData("Robot direction: ", robot.gyro.getHeading() + 45);
	}
}
