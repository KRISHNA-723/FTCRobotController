package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.IntakeMode;


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
        boolean leftTrig = gamepad1.left_bumper; // Read left bumper input

        if (leftTrig) {
            intake.setMotorSpeed(0.5); // Run intake at 50% power
        } else {
            intake.setMotorSpeed(0); // Stop intake
        }

    }

}