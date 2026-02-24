package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// Class used for the functioning of the robot's intake
public class IntakeMode {

    private DcMotor Intake; // Left Motor for intake
    private Servo servoIntake; // Right Motor for intake


    // Create init() method which takes in hardware map
    // Hardware map is used to link the different motors to their configured ports
    public void init(HardwareMap hwMap) {

        // Configuring the motors for the intake
        Intake = hwMap.get(DcMotor.class, "Intake");
        servoIntake = hwMap.get(Servo.class, "servoIntake");

        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // Method to set the motor's speed
    public void setMotorSpeed(double speed) {

        // Set the motors speed to the given speed argument
        Intake.setPower(speed);
        servoIntake.setPosition(speed);
    }


}
