using System;

namespace MySecret
{
    internal class Program
    {

        private static readonly string flag = "H{w0w_w0w_w0w_i_didn7_exp3ct_th4t!}";
        
        public static void Main(string[] args)
        {
            Console.WriteLine("Welcome to my secret!");
            Console.Write("Please enter the password: ");
            Console.ReadLine();
            Console.WriteLine("Wrong password!");
        }
    }
}