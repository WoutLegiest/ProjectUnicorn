pkg load signal 
data = [1.0211906, 0.98194313, 1.0996008, 1.099576, 1.0995617, 1.0210657, 0.98182964, 0.98181343, 1.0994692, 0.90330124, 0.94251156, 1.0209551, 0.9424896, 0.9816923, 0.9816761, 0.9031906, 0.94236565, 0.9815788, 0.9815693, 1.0599957, 1.0207491, 1.0599451, 1.0599298, 0.9814539, 0.9422083, 1.0598669, 1.0990772, 1.059824, 0.98136044, 1.09902, 0.9420862, 1.0597572, 0.94204426, 0.98125076, 1.1381397, 1.0596666, 0.94197845, 1.0204182, 1.0988703, 1.0203991, 1.0596132, 0.9026966, 1.0203533, 0.90266514, 0.94187546, 0.94187546, 1.0595407, 0.94183445, 0.9418278, 0.98105335, 0.94182587, 0.9025841, 0.94179916, 0.98101044, 0.9417753, 0.8633251, 0.9417658, 1.0594435, 1.0594444, 1.0594406, 1.0202093, 0.94174576, 1.0594187, 1.0201826, 1.020175, 0.9809418, 0.9417105, 0.9809294, 1.0593786, 1.020153, 0.98092175, 0.9809122, 1.0201302, 1.0985737, 0.9808912, 1.020112, 1.0593357, 0.94165707, 1.0985622, 1.0201168, 1.0201101, 1.0201044, 0.9808712, 1.0593281, 1.0200968, 1.0200949, 1.0593147, 0.82398415, 1.0200787, 0.9808693, 0.94164276, 1.0985508]; 
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
