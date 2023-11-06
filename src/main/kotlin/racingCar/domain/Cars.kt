package racingCar.domain

import racingCar.domain.strategy.MoveStrategy
import racingCar.domain.strategyImpl.RandomStrategy
import racingCar.error.ErrorMessage

class Cars(private val moveStrategy: MoveStrategy = RandomStrategy(), private var carList: List<Car> = emptyList()) {

    fun getCarList(): List<Car> {
        return carList
    }

    fun initCars(inputCars: String, delimiter: String = ",") {
        val split = inputCars.split(delimiter)
        split.forEach { require(it.length <= LIMIT_CAR_NAME) { ErrorMessage.NAME_TOO_LONG } }

        val carsNames: List<String> = split
        carList = carsNames.map { Car(name = it, moveStrategy = moveStrategy) }
    }

    fun getWinners(): List<Car> {
        val maxMoveCount = carList.maxByOrNull { it.moveCount }?.moveCount
        return carList.filter { it.moveCount == maxMoveCount }
    }

    companion object {
        private const val LIMIT_CAR_NAME = 5
    }
}
