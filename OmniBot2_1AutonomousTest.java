package org.firstinspires.ftc.OmniBot2_1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "OmniBot 2.1 Beacon Test", group = "OmniBot 2.1")
//@Disabled
public class OmniBot2_1AutonomousTest extends LinearOpMode {

    private OmniBot2_1Hardware robot = new OmniBot2_1Hardware();

    private ElapsedTime pressButtonT = new ElapsedTime();
    private ElapsedTime moveT = new ElapsedTime();

    double kp = 1/300;
    //double ki = 1/300;
    double kd = 1/300;

    double p = 0;
    //double i = 0;
    double d = 0;

    double finalPD = 0;

    double lastError = 0;

    @Override
    public final void runOpMode() {

        robot.init(hardwareMap);

        //moveFor(1,1,1,1, 2);
        //moveFor(-1,-1,-1,-1, 2);

        //moveTo(2,2,2,2);

        //moveFor(-1,-1,-1,-1, 1);

        //moveUntil(1,1,1,1, moveT.time() > 2);

        //pressLeftButton(3);

        //pressRightButton(3);

        waitForStart();
        telemetry.addData("Status: ", "starting");

        moveTo(-2,-2,-2,-2);

        //moveFor(1,1,1,1, 1);

        //moveUntil(-1,-1,-1,-1, moveT.time() > 2);

//------------------- for red -----------------------------------------------\\

        turnTo(45);

        //moveUntil(1,1,1,1, robot.light.getLightDetected() > 60);

        ////check if the beacon is red
        //if (robot.lColor.red() > 150 && robot.lColor.blue() < 100) {
        //    telemetry.addData("Left Button Color: ", "Red");
        //    pressLeftButton(0.5);
        //}
        //else {
        //    telemetry.addData("Left Button Color: ", "Blue");
        //    pressRightButton(0.5);
        //}
    }

    //void pressLeftButton(double extendTime) {
    //    telemetry.addData("Status: ", "Pressing Button");
    //    pressButtonT.reset();
    //    while (pressButtonT.time() <= extendTime && opModeIsActive()) {
    //        robot.button1.setPower(1);
    //    }
    //    while (pressButtonT.time() <= extendTime*2 && opModeIsActive()) {
    //        robot.button1.setPower(-1);
    //    }
    //    robot.button1.setPower(0);
    //    telemetry.addData("Status: ", "Doing Nothing");
    //    telemetry.update();
    //}
    //
    //void pressRightButton(double extendTime) {
    //    telemetry.addData("Status: ", "Pressing Button");
    //    pressButtonT.reset();
    //    while (pressButtonT.time() <= extendTime && opModeIsActive()) {
    //        robot.button2.setPower(1);
    //    }
    //    while (pressButtonT.time() <= extendTime*2 && opModeIsActive()) {
    //        robot.button2.setPower(-1);
    //    }
    //    robot.button2.setPower(0);
    //    telemetry.addData("Status: ", "Doing Nothing");
    //    telemetry.update();
    //}

    void moveFor(double Fl, double Fr, double Bl, double Br, double moveTime) {
        telemetry.addData("Status: ", "Moving");
        moveT.reset();
        while (moveT.time() <= moveTime && opModeIsActive()) {
            robot.motorFl.setPower(Fl);
            robot.motorFr.setPower(Fr);
            robot.motorBl.setPower(Bl);
            robot.motorBr.setPower(Br);
        }
        robot.motorFl.setPower(0);
        robot.motorFr.setPower(0);
        robot.motorBl.setPower(0);
        robot.motorBr.setPower(0);
        telemetry.addData("Status: ", "Doing Nothing");
        telemetry.update();
    }

    void moveUntil(double Fl, double Fr, double Bl, double Br, boolean thingIsTrue) {
        telemetry.addData("Status: ", "Moving");
        while (thingIsTrue && opModeIsActive()) {
            robot.motorFl.setPower(Fl);
            robot.motorFr.setPower(Fr);
            robot.motorBl.setPower(Bl);
            robot.motorBr.setPower(Br);
        }
        robot.motorFl.setPower(0);
        robot.motorFr.setPower(0);
        robot.motorBl.setPower(0);
        robot.motorBr.setPower(0);
        telemetry.addData("Status: ", "Doing Nothing");
        telemetry.update();
    }

    void moveTo(double FlEnc, double FrEnc, double BlEnc, double BrEnc) {
        telemetry.addData("Status: ", "Moving");
        robot.motorFl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorFr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorBr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motorFl.setTargetPosition(robot.motorFl.getCurrentPosition() + (int)Math.round(FlEnc * 560));
        robot.motorFr.setTargetPosition(robot.motorFr.getCurrentPosition() + (int)Math.round(FrEnc * 560));
        robot.motorBl.setTargetPosition(robot.motorBl.getCurrentPosition() + (int)Math.round(BlEnc * 560));
        robot.motorBr.setTargetPosition(robot.motorBr.getCurrentPosition() + (int)Math.round(BrEnc * 560));

        robot.motorFl.setPower(0.1);
        robot.motorFr.setPower(0.1);
        robot.motorBl.setPower(0.1);
        robot.motorBr.setPower(0.1);

        while (robot.motorFl.isBusy()&& robot.motorFr.isBusy()&& robot.motorBl.isBusy()&& robot.motorBr.isBusy()&& opModeIsActive()) {
            telemetry.addData("Front Left Encoder: ",  robot.motorFl.getCurrentPosition());
            telemetry.addData("Front Left Target: ",   robot.motorFl.getTargetPosition());
            telemetry.addData("Front Right Encoder: ", robot.motorFr.getCurrentPosition());
            telemetry.addData("Back Left Encoder: ",   robot.motorBl.getCurrentPosition());
            telemetry.addData("Back Right Encoder: ",  robot.motorBr.getCurrentPosition());
            telemetry.update();
        }

        robot.motorFl.setPower(0);
        robot.motorFr.setPower(0);
        robot.motorBl.setPower(0);
        robot.motorBr.setPower(0);

        robot.motorFl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorFr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorBl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorBr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status: ", "Doing Nothing");
        telemetry.update();
    }

    void turnTo(double direction) {
        telemetry.addData("Status: ", "Turning");

        while (robot.gyro.getHeading() != direction && opModeIsActive()) {
            p = robot.gyro.getHeading() - direction;
            //i =+ p;
            d = p - lastError;
            lastError = p;
            double finalPD = p * kp + d * kd;

            robot.motorFl.setPower(-finalPD);
            robot.motorFr.setPower( finalPD);
            robot.motorBl.setPower(-finalPD);
            robot.motorBr.setPower( finalPD);
        }

        telemetry.addData("Status: ", "Doing Nothing");
        telemetry.update();
    }
}