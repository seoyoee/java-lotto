package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.Result.*;

public class LottoResult {
    private int equalCount;
    private boolean isBonus;

    public Map<Result, Integer> getResult(List<Lotto> lottos, WinNumber winNumber, BonusNumber bonusNumber) {
        Map<Result, Integer> lottoResult = new HashMap<>();
        initLottoResult(lottoResult);

        for (Lotto lotto : lottos) {
            initValue();
            getEqualCount(lotto, winNumber.winNumbers);

            if (equalCount == 5) {
                getIsBonus(lotto, bonusNumber.bonusNumber);
            }

            lottoResult.put(getRanking(), lottoResult.get(getRanking()) + 1);
        }
        return lottoResult;
    }

    private void initValue() {
        equalCount = 0;
        isBonus = false;
    }

    private void initLottoResult(Map<Result, Integer> lottoResult) {
        lottoResult.put(FIRST, 0);
        lottoResult.put(SECOND, 0);
        lottoResult.put(THIRD, 0);
        lottoResult.put(FOURTH, 0);
        lottoResult.put(FIFTH, 0);
        lottoResult.put(NO_REWARD, 0);
    }

    private void getEqualCount(Lotto lotto, List<Integer> winNumbers) {
        for (int i : lotto.getNumbers()) {
            if (winNumbers.contains(i)) {
                equalCount++;
            }
        }
    }

    private void getIsBonus(Lotto lotto, int bonusNumber) {
        for (int i : lotto.getNumbers()) {
            if (i == bonusNumber) {
                isBonus = true;
                break;
            }
        }
    }

    private Result getRanking() {
        if (equalCount == 6) {
            return FIRST;
        }
        if (equalCount == 5 && isBonus) {
            return SECOND;
        }
        if (equalCount == 5) {
            return THIRD;
        }
        if (equalCount == 4) {
            return FOURTH;
        }
        if (equalCount == 3) {
            return FIFTH;
        }
        return NO_REWARD;
    }
}
