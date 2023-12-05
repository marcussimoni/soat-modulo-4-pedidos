package br.com.fiapsoat.controller;

import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;

public interface ApplicationStatusController {

    Boolean updateLivenessState(LivenessState state);

    Boolean updateReadinessState(ReadinessState state);

}
