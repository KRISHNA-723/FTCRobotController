package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Class used for the functioning of the robot's intake
public class IntakeMode {

    private DcMotor leftIntake; // Left Motor for intake
    private DcMotor rightIntake; // Right Motor for intake



    // Create init() method which takes in hardware map
    // Hardware map is used to link the different motors to their configured ports
    public void init(HardwareMap hwMap) {

        // Configuring the motors for the intake
        leftIntake = hwMap.get(DcMotor.class, "leftIntake");
        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // Use constant power for the motor

        rightIntake = hwMap.get(DcMotor.class, "rightIntake");
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // Method to set the motor's speed
    public void setMotorSpeed(double speed) {

        // Set the motors speed to the given speed argument
        leftIntake.setPower(speed);
        rightIntake.setPower(-speed);
    }


}
