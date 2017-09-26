program firstDeliverable

        integer :: a, b, c = 0

        character(len=6) :: string1, string2

        logical :: bool

        print *, "Enter two numbers to be added together:"

        read *, a

        read *, b

        c = a + b

        print *, "The result is ", c

        print *, "Enter a word of six letters or less"

        read *, string1

        print *, "Enter a second word of six letters or less"

        read *, string2

        bool = string1 == string2

        if (bool) then

        print *, "The words entered are identical"

        else

        print *, "The words entered are different"

        end if



end program firstDeliverable