package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static com.qualcomm.robotcore.util.Range.clip;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

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

        robotDir  = -Math.toRadians(robot.gyro.getHeading() + 45);

        // movement code, Gamepad 1 controls movement with left stick and eventually turning with right stick
        Flpower =  cos(robotDir + joyTheta) * joyRadius + clip( gamepad1.right_stick_x, -0.7, 0.7);
        Frpower = -sin(robotDir + joyTheta) * joyRadius + clip(-gamepad1.right_stick_x, -0.7, 0.7);
        Blpower = -sin(robotDir + joyTheta) * joyRadius + clip( gamepad1.right_stick_x, -0.7, 0.7);
        Brpower =  cos(robotDir + joyTheta) * joyRadius + clip(-gamepad1.right_stick_x, -0.7, 0.7);

		robot.motorFl.setPower(Flpower);
		robot.motorFr.setPower(Frpower);
		robot.motorBl.setPower(Blpower);
		robot.motorBr.setPower(Brpower);

		//Send telemetry data back to driver station.
		telemetry.addData("stick X: ", -gamepad1.left_stick_x);
		telemetry.addData("stick Y: ", -gamepad1.left_stick_y);
        telemetry.addData("stick Theta: ",  joyTheta );
		telemetry.addData("stick Radius: ", joyRadius);
        telemetry.addData("Robot direction: ", robot.gyro.getHeading() + 45);
	}
}
