program thirdDeliverable

        implicit none
        
        integer, dimension(5) :: x      
		        
        integer :: i
        
        x(1) = 1

        x(2) = 2

        x(3) = 3

        x(4) = 4

        x(5) = 5

        i = 1
        
        do while(i <= 5 )
                
        print *, x(i)
        
        i = i + 1
        
        end do

        call scalarmult(x)

        i = 1

        do while(i <= 5 )
                
        print *, x(i)
        
        i = i + 1
        
        end do
        
end program thirdDeliverable

subroutine scalarmult(x)

        integer, intent(inout) :: x(5)
		

        x = x * 5

end subroutine
