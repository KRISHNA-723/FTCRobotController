package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TeleOP")

public class Omni6Wheels extends LinearOpMode {

    // Attributes of class, mapping motors to wheels
    frontLeft =hardwareMap.get(DcMotor .class,"frontLeft");
    middleLeft =hardwareMap.get(DcMotor .class,"middleLeft");
    backLeft =hardwareMap.get(DcMotor .class,"backLeft");

    frontRight =hardwareMap.get(DcMotor .class,"frontRight");
    middleRight =hardwareMap.get(DcMotor .class,"middleRight");
    backRight =hardwareMap.get(DcMotor .class,"backRight");

    // Direction is kept consistent
    frontLeft.setDirection(DcMotor.Direction.FORWARD);
    middleLeft.setDirection(DcMotor.Direction.FORWARD);
    backLeft.setDirection(DcMotor.Direction.FORWARD);

    frontRight.setDirection(DcMotor.Direction.REVERSE);
    middleRight.setDirection(DcMotor.Direction.REVERSE);
    backRight.setDirection(DcMotor.Direction.REVERSE);

    waitForStart();

    while(opModeIsActive()) {

        // STRAFE LEFT
        if (gamepad1.left_bumper) {
            strafeLeft(0.5);
        }
        // STRAFE RIGHT
        else if (gamepad1.right_bumper) {
            strafeRight(0.5);
        }
        // STOP if no input
        else {
            stopMotors();
        }
    }

    // Going left with omni wheels
    private void strafeLeft(double power) {
        frontLeft.setPower(power * 0.3);
        middleLeft.setPower(power * 0.3);
        backLeft.setPower(power);

        frontRight.setPower(-power * 0.3);
        middleRight.setPower(-power * 0.3);
        backRight.setPower(-power);
    }

    // Going right with omni wheels
    private void strafeRight(double power) {
        frontLeft.setPower(-power * 0.3);
        middleLeft.setPower(-power * 0.3);
        backLeft.setPower(-power);

        frontRight.setPower(power * 0.3);
        middleRight.setPower(power * 0.3);
        backRight.setPower(power);
    }

    // To stop the robot
    private void stopMotors() {
        frontLeft.setPower(0);
        middleLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        middleRight.setPower(0);
        backRight.setPower(0);
    }
}


