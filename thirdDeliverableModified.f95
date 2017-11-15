SUBROUTINE incrementOne(byOne)

    IMPLICIT NONE

    INTEGER, INTENT(INOUT) :: byOne

    byOne = byOne + 1

END SUBROUTINE incrementOne


program thirdDeliverable

        implicit none
        
        integer, dimension(5) :: x

        integer :: y, byOne, byTwo

        integer :: i

        byOne = 10

        print *, "byOne = ", byOne

        CALL incrementOne(byOne)

        print *, "byOne = ", byOne

        byTwo = 10

        print *, "byTwo = ", byTwo

        byTwo = incrementTwo(byTwo)

        print *, "byTwo = ", byTwo

        x(1) = 1

        x(2) = 2

        x(3) = 3

        x(4) = 4

        x(5) = 5

        y = 5

        i = 1

        do while(i <= 5 )

        print *, x(i)

        i = i + 1

        end do

        call scalarmult(x, y)

        i = 1

        do while(i <= 5 )

        print *, x(i)

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

subroutine scalarmult(x, y)

        integer, intent(inout) :: x(5), y

        x = x * y

end subroutine scalarmult
