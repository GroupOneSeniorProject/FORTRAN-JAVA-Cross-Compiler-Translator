SUBROUTINE incrementOne(byOne)

    IMPLICIT NONE

    INTEGER, INTENT(INOUT) :: byOne

    byOne = byOne + 1

END SUBROUTINE incrementOne


program thirdDeliverable

        implicit none
        
        integer, dimension(5) :: xVar

        integer :: yVar, byOne, byTwo

        integer :: i

        byOne = 10

        print *, "byOne equals ", byOne

        CALL incrementOne(byOne)

        print *, "byOne equals ", byOne

        byTwo = 10

        print *, "byTwo equals ", byTwo

        byTwo = incrementTwo(byTwo)

        print *, "byTwo equals ", byTwo

        xVar(1) = 1

        xVar(2) = 2

        xVar(3) = 3

        xVar(4) = 4

        xVar(5) = 5

        yVar = 5

        i = 1

        do while(i <= 5 )

        print *, xVar(i)

        i = i + 1

        end do

        call scalarmult(xVar, yVar)

        i = 1

        do while(i <= 5 )

        print *, xVar(i)

        i = i + 1

        end do

        CONTAINS

            INTEGER FUNCTION incrementTwo(byTwo)

                IMPLICIT NONE

                INTEGER, INTENT(IN) :: byTwo

                incrementTwo = byTwo + 2

                RETURN

            END FUNCTION incrementTwo

end program thirdDeliverable

subroutine scalarmult(xVar, yVar)

        integer, intent(inout) :: xVar(5), yVar

        xVar = xVar * yVar

end subroutine scalarmult