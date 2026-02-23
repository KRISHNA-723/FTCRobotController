package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IntakeMode;

@TeleOp(name = "Intake Testing")
// Testing the intake in the main TeleOp
public class IntakeTesting extends OpMode {

    // Instantiating the IntakeMode class inside the OpMode class
    IntakeMode intake = new IntakeMode();

    @Override
    public void init() {
        intake.init(hardwareMap); // Get the hardware map for the intake
    }


    @Override
    public void loop() {
        boolean leftBump = gamepad1.left_bumper; // Read left bumper input

        if (leftBump) {
            intake.setMotorSpeed(1.0); // Run intake at 50% power

        }
    }
}

