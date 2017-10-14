program secondDeliverable

        implicit none

        integer :: a, b, c, acc, intake = 0

        real :: x, y, z = 0

        real, external :: functest

        character(len=6) :: string1, string2

        logical :: bool

        print *, "Enter two numbers to be added together:"

        read *, a

        read *, b

        c = a + b

        print *, "The result is ", c

        print *, "Enter a word of six letters or less"

        read *, string1

        print *, "Enter a second letter of six letters or less"

        read *, string2
        
        bool = string1 == string2

        if (bool) then
        
        print *, "The words entered are identical"

        else
        
        print *, "The words entered are different"

        end if

        print *, "Enter a value of X to find the Sin of"

        read *, x

        print *, "Enter a value of Y to find the Cos of"

        read *, y

        print *, "Enter a value of Z to find the Absolute value of"

        read *, z

        x = sin(x)

        y = cos(y)

        z = abs(z)

        print *, "The Sin of X is ", x

        print *, "The Cos of Y is ", y

        print *, "The Absolute Value of Z is ", z

        print *, "X is ", x

        z = functest(z, x)

        print *, "X is ", x

        print *, "Z ** Z is ", z

        intake = 1

        acc = 0

        do while(intake /= 0)

        print *, "Enter an integer to add to total. Entry ends with 0 "

        read *, intake

        acc = acc +  intake

        end do 

        print *, "The sum is ", acc

end program secondDeliverable

real function functest(z, x)

        implicit none

        real, intent(in) :: z, x

        functest = z ** z

        return

end function functest
