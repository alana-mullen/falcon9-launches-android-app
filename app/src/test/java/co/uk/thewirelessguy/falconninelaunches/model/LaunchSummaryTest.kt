package co.uk.thewirelessguy.falconninelaunches.model

import co.uk.thewirelessguy.falconninelaunches.model.api.LaunchesResponse
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class LaunchSummaryTest : FreeSpec() {

    private val launchesResponse = LaunchesResponse(
        id = "some-id",
        name = "some-rocket",
        dateUTC = "2006-03-24T22:30:00.000Z",
        success = true,
        links = LaunchesResponse.Links(
            patch = LaunchesResponse.Patch(
                small = "some-image"
            )
        )
    )

    init {
        "from (LaunchSummary)" - {
            "When given a correct LaunchesResponse it returns correct data" {
                val launchSummary = LaunchSummary.from(launchesResponse)
                with (launchSummary!!) {
                    id shouldBe "some-id"
                    title shouldBe "some-rocket"
                    launchDate shouldBe "24-03-2006"
                    missionSuccess shouldBe true
                    image shouldBe "some-image"
                }
            }

            "When given incorrect LaunchesResponse data it returns false" - {

                "Field required for id is not supplied" {
                    val launchSummary = LaunchSummary.from(launchesResponse.copy(
                        id = ""
                    ))
                    launchSummary shouldBe null
                }

                "Field required for name is not supplied" {
                    val launchSummary = LaunchSummary.from(launchesResponse.copy(
                        name = ""
                    ))
                    launchSummary shouldBe null
                }

                "Field required for date is not supplied" {
                    val launchSummary = LaunchSummary.from(launchesResponse.copy(
                        dateUTC = ""
                    ))
                    launchSummary!!.launchDate shouldBe ""
                }
            }
        }

        "from (List<LaunchSummary>)" - {
            "If LaunchesResponse list is empty then it returns an empty list" {
                val launchSummary = LaunchSummary.from(listOf())
                launchSummary shouldBe emptyList()
            }

            "If LaunchesResponse list is comprised of some number of valid launches it returns that same number of correct LaunchSummary objects" - {

                "A single launch" {
                    val launchSummary = LaunchSummary.from(listOf(launchesResponse))
                    with (launchSummary.first()) {
                        id shouldBe "some-id"
                        title shouldBe "some-rocket"
                        launchDate shouldBe "24-03-2006"
                        missionSuccess shouldBe true
                        image shouldBe "some-image"
                    }
                }

                "More than one launch" {
                    val launchSummary = LaunchSummary.from(
                        listOf(launchesResponse, launchesResponse.copy(name = "another-rocket"))
                    )
                    with (launchSummary.first()) {
                        id shouldBe "some-id"
                        title shouldBe "some-rocket"
                        launchDate shouldBe "24-03-2006"
                        missionSuccess shouldBe true
                        image shouldBe "some-image"
                    }
                    with (launchSummary.last()) {
                        title shouldBe "another-rocket"
                    }
                }
            }

            "If LaunchesResponse list is comprised of some number of invalid launches it doesn't return that same number of LaunchSummary objects" - {

                "If all launches are bad data it returns an empty list" {
                    val launchSummary = LaunchSummary.from(
                        listOf(
                            launchesResponse.copy(id = null),
                            launchesResponse.copy(name = null))
                    )
                    launchSummary shouldBe emptyList()
                }

                "More than one launch with some good and some bad data will return only the good data" {
                    val launchSummary = LaunchSummary.from(
                        listOf(
                            launchesResponse, // Good data
                            launchesResponse.copy(name = null))
                    )
                    with (launchSummary.first()) {
                        id shouldBe "some-id"
                        title shouldBe "some-rocket"
                        launchDate shouldBe "24-03-2006"
                        missionSuccess shouldBe true
                        image shouldBe "some-image"
                    }
                }
            }
        }
    }
}