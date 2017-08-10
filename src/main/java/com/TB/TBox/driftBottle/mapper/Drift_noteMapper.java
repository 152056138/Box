package com.TB.TBox.driftBottle.mapper;

import java.util.List;

import com.TB.TBox.driftBottle.bean.Drift_evaluate;
import com.TB.TBox.driftBottle.bean.Drift_note;

public interface Drift_noteMapper {
	public void addDrift_note(Drift_note drift_note);

	public void updateDrift_note(Drift_note drift_note);

	public Drift_note selectDrift_noteByID(int driftId);

	public void addDrift_evaluate(Drift_evaluate drift_evaluate);

	public List<Drift_evaluate> selectAllDrift_note_evaluate(int driftId);

	public List<Drift_note> randomSelectDrift_note(int hate);
}
