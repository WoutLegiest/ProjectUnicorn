pkg load signal 
data = [-0.027874075, -0.027329974, -0.009801216, -0.026792377, -0.0022987425, -4.4941157E-4, -0.02664487, -0.008193344, 0.0030509233, 0.009234257, 0.003106758, -0.002758555, 0.0072734356, 0.017841205, 0.014076784, 0.019868784, 0.0060959533, 0.011701256, 0.017229505, 0.025241062, 0.015877478, -0.002330467, 0.0133882165, 0.018593565, 0.0077953637, 0.020234063, 0.008809313, 0.02804862, 0.018906653, -0.001721099, 0.011145383, 0.021554902, 0.035746202, -0.008869201, 0.004520729, 0.010095909, 0.034363016, 0.034178123, 0.024702966, 0.02031906, 0.008684367, 0.03508325, 0.023393258, -2.809465E-4, 0.0040065497, 0.04256621, 0.044595435, -0.051655293, -0.05354645, 0.182282, 0.24578314, 0.007080272, -0.45179087, 0.14204271]; 
endData = length(data) -1 ; 
 endData = endData*0.01; 
 t = 0:0.01:endData; 
 %% Step 1: resample at fixed time step 
Fs = 100.; % desired (fixed) sample rate 
%[data_resampled,t_resampled] = resample(data,t,Fs); 
t_resampled = t(1):1/Fs:t(end); 
data_resampled = interp1(t, data, t_resampled, 'spline'); 
% to make sure time starts at t = 0s: 
t_resampled = t_resampled - t_resampled(1); 
%% Step 2: Compute amplitude spectrum of the signal 
L = length(data_resampled); 
f = Fs*(0:(L/2))/L; 
A2_data = fft(data_resampled); A2 = abs(A2_data/L); 
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); 
maxi = max(A_data); 
k = find(A_data==maxi); 
disp(f(k)); 
disp('fase'); 
disp(A_data); 
disp('fase'); 
disp(f); 
disp('fase'); 
%% Step 3: Apply bandbass filter 
% Lowerbound and upperbound cutoff bandpass filter (relative to Nyquist frequency) 
f1 = 1/Fs*2; f2 = 4/Fs*2; 
% [b,a] = butter(n,Wn,ftype) 
filter_order = 4; 
[b,a] = butter(filter_order,[f1 f2]); 
data_filtered = filtfilt(b,a,data_resampled); 
A2_data = fft(data_filtered); A2 = abs(A2_data/L); 
A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); 
maxi = max(A_data); 
k = find(A_data==maxi); 
disp(f(k)); 
disp('fase'); 
disp(A_data); 
